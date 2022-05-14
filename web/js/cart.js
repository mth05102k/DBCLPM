/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let currentBill = 0;
let totalBill = 0;
const billContainerEle = document.getElementById('bill-container');

function checkBillUpdate(updatedItem, checkboxChecked) {
    if (typeof updatedItem === 'object') {
//        checkbox update event
        if (checkboxChecked !== null) {
            const changeAmount = document.querySelector('.item-total-price > input[type="hidden"]').value;

            if (checkboxChecked) {
                currentBill += changeAmount;
            } else {
                currentBill -= changeAmount;
            }

            billContainerEle.innerText = currentBill;
        }

        const selector = updatedItem.querySelector('.item-selector');
        if (selector.checked) {
            updateBill();
        }
    } else if (typeof updatedItem === 'string' && updatedItem === 'all') {

    }

    function updateBill() {

    }
}

//select all checkbox
(function () {
    const billContainer = document.getElementById('bill-container');
    const totalBillInput = document.querySelector('.item-total-bill__input');
    const allCheckbox = Array.from(document.getElementsByClassName('item-selector'));

    allCheckbox.forEach((element) => {
        element.addEventListener('change', function () {
//            checkbox change -> update total price
            const container = this.closest('[itemid]');
            const price = Number(container.querySelector('.item-total-price__input').value);

            if (this.checked) {
                const newPrice = Number(totalBillInput.value) + price;
                totalBill = newPrice;

                billContainer.innerHTML = new Intl.NumberFormat().format(newPrice) + "<sup>đ</sub>"
                totalBillInput.value = newPrice;
            } else {
                const newPrice = Number(totalBillInput.value) - price;
                totalBill = newPrice;

                billContainer.innerHTML = new Intl.NumberFormat().format(newPrice) + "<sup>đ</sub>"
                totalBillInput.value = newPrice;
            }
        });
    })

//  select all checkbox -> update total price
    document.getElementById('select-all-checkbox').addEventListener('change', function () {
        const checkboxStatus = this.checked;

        allCheckbox.forEach((element) => {
            element.checked = checkboxStatus;
            element.dispatchEvent(new Event("change"))
        });

        billContainer.innerHTML = new Intl.NumberFormat().format(totalBill) + "<sup>đ</sub>";
        totalBillInput.value = totalBill;
    });

})();

//update item quantity
(function () {
    const checkItemAvailable = async (itemId, quantity) => {
        const response = await fetch('http://localhost:8080/g11/product/api/available', {
            method: 'POST',
            body: new URLSearchParams({
                itemId,
                quantity
            })
        });

        const data = await response.text();
        if (data) {
            return data.split(';')[0];
        }

        return '503';
    }

    const calcNewQuantity = (quantity, action) => {
        switch (action) {
            case 'plus':
                return quantity + 1;
            case 'minus':
                const newQuantity = quantity - 1;
                return newQuantity < 1 ? 1 : newQuantity;
            default:
                return quantity;
        }

    }

    async function updateItemQuantity(action, updateControlEle) {
        const input = $(updateControlEle).parent().find('input');
        const itemElement = updateControlEle.closest('.item[itemid]');
        const itemId = itemElement.getAttribute('itemId');
        const itemUnitPrice = itemElement.querySelector(`#item-unit-price-${itemId}`).value;

//            disable more event
        Object.assign(updateControlEle.parentElement.style,
                {
                    pointerEvents: 'none',
                    opacity: '0.4'
                }
        );

        const quantity = +(input.val());
        const newQuantity = calcNewQuantity(quantity, action);
        const responseCode = await checkItemAvailable(itemId, newQuantity);

        if (responseCode === '200') {
            const priceInputContainer = itemElement.querySelector('.item-total-price > input[type="hidden"]');
            const priceDisplayContainer = itemElement.querySelector('#item-total-price-display');

            input.val(newQuantity);
            input.change();

            const newTotalCost = (+itemUnitPrice) * newQuantity;
            priceInputContainer.value = newTotalCost;
            priceDisplayContainer.innerHTML = new Intl.NumberFormat({maximumSignificantDigits: 3}).format(newTotalCost) + '<sup>đ</sup>';
        } else if (responseCode === '406') {
            Swal.fire({
                icon: 'error',
                title: 'Sản phẩm không còn đủ hàng',
                timer: 2000,
                showCancelButton: false,
                showConfirmButton: true,
            })
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Đã có lỗi xảy ra, vui lòng thử lại sau',
                timer: 2000,
                showCancelButton: false,
                showConfirmButton: false,
            });
        }

//          active more event
        Object.assign(updateControlEle.parentElement.style,
                {
                    pointerEvents: null,
                    opacity: null
                }
        );
    }

//    add update quantity event listener
    Array.from($('.item-counter > .minus')).forEach((ele) => {
        const action = 'minus';
        ele.addEventListener('click', () => updateItemQuantity(action, ele));
    });

    Array.from($('.item-counter > .plus')).forEach((ele) => {
        const action = 'plus';
        ele.addEventListener('click', () => updateItemQuantity(action, ele));
    });

})();

//remove item in cart
(function () {
    const removeItemFromCart = async (itemId) => {
        const response = await fetch('http://localhost:8080/g11/cart/api/delete', {
            method: 'POST',
            body: new URLSearchParams({
                itemId
            })
        });

        const data = await response.text();
        if (data) {
            return data.split(';')[0];
        }

        return '503';
    }

    Array.from(document.getElementsByClassName('delete-item-btn')).forEach((element) => {
        element.addEventListener('click', async function () {
            const itemElement = document.querySelector(`.item[itemId="${this.getAttribute('for')}"]`);
            const itemId = itemElement.getAttribute('itemId');

            const responseCode = await removeItemFromCart(itemId);

            if (responseCode === '201') {
                itemElement.remove();
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Đã có lỗi xảy ra, vui lòng thử lại sau',
                    timer: 2000,
                    showCancelButton: false,
                    showConfirmButton: false,
                });
            }
        });
    })
})();


//pre fill info for create order
(function () {
    const payNowBtn = document.getElementById('pay-now');
    payNowBtn.addEventListener('click', () => {
        let haveItemSelected = false;

        //        get selected item and convert to string
        let selectedItem = Array.from(document.querySelectorAll('.item-selector:checked')).reduce((total, element, index) => {
            haveItemSelected = true;
            const itemEle = element.closest('[itemId]');
            
            const itemId = itemEle.getAttribute('itemId');
            const quantity = itemEle.querySelector(`#item-counter-${itemId} input`).value;

            return total + `"${itemId}":${quantity},`;
        }, '{');

        selectedItem = selectedItem.substring(0, selectedItem.length - 1) + "}"

        if (haveItemSelected) {
            //    save selected item to session storage
            window.sessionStorage.setItem('selectedItem', selectedItem);

            window.location.href = "http://localhost:8080/g11/checkout/fill-info";
        } else {
            Swal.fire({
                title: 'Bạn chưa chọn mặt hàng nào để thanh toán!',
                text: "Vui lòng chọn ít nhất một mặt hàng trong giỏ hàng",
                icon: 'warning',
                showConfirmButton: true,
                showCancelButton: false,
            });
        }
    });

})();