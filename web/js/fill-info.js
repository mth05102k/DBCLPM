/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const totalPriceEle = document.getElementById('item-total-price');
let customerAddressFilled = false;

function jsonEscape(str) {
    return str.replace(/\n/g, "\\\\n").replace(/\r/g, "\\\\r").replace(/\t/g, "\\\\t");
}

// get shipment info and update shipment cost
(async function () {
    const shipmentTypeSelectEle = document.getElementById('shipment-type-select');
    const shipmentCostEle = document.getElementById('shipping-cost');
    const totalBillEle = document.getElementById('total-bill');

    const shopId = 2371719;
    const shopDistrictId = 1484;

    const getCustomerAddressInfo = async () => {
        const response = await fetch('http://localhost:8080/g11/user/api/get-address', {
            method: 'GET',
        });

        const data = await response.text();
        if (data) {
            const dataTokens = data.split(';');

            if (dataTokens[0] === '200') {
                return JSON.parse(dataTokens[1]);
            }
        }

        return null;
    }

    const getShipmentList = async (shopId, fromDistrict, toDistrict) => {
        const response = await fetch('https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Token': '92ba9c57-658c-11ec-9054-0a1729325323'
            },
            body: JSON.stringify({
                "shop_id": shopId,
                "from_district": fromDistrict,
                "to_district": toDistrict
            })
        });

        const data = await response.json();
        if (data && data.code === 200) {
            return data.data;
        }

        return null;
    }

    const updateSelectedShipmentCost = async (e) => {
        const serviceId = Number(e.target.value);
        if (serviceId === 0) {
            return;
        }

        const response = await fetch('https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Token': '92ba9c57-658c-11ec-9054-0a1729325323',
                "ShopId": shopId,
            },
            body: JSON.stringify({
                "from_district_id": shopDistrictId,
                "service_type_id": serviceId,
                "to_district_id": Number(customerAddressInfo.shipmentDistrictId),
//                "to_ward_code": "21012",
                "height": 20,
                "length": 35,
                "weight": 300,
                "width": 30,
                "insurance_value": 50000,
                "coupon": null
            })
        });

        const data = await response.json();
        if (data.code === 200) {
            const totalShipmentFee = data.data.total;

            shipmentCostEle.value = totalShipmentFee;
            shipmentCostEle.innerHTML = `${new Intl.NumberFormat().format(totalShipmentFee)}<sup>??</sup>`;
            totalBillEle.innerHTML = `${new Intl.NumberFormat().format(totalShipmentFee + totalPriceEle.value)}<sup>??</sup>`;
        } else {
            console.log(data.message)
        }

    }

    const customerAddressInfo = await getCustomerAddressInfo();

    if (customerAddressInfo === null) {
        console.error('GET http://localhost:8080/g11/user/api/get-address FAILED')
        return;
    }

    const shipmentDistrictId = Number(customerAddressInfo.shipmentDistrictId);
    if (!shipmentDistrictId) {
        customerAddressFilled = false;
        document.getElementById('lack-individual-info-msg').innerText = 'Th??ng tin nh???n h??ng c??n thi???u. '
                + 'Vui l??ng ??i???n ?????y ????? th??ng tin nh???n h??ng trong m???c "T??i Kho???n C???a T??i"';

        return;
    } else {
        customerAddressFilled = true;
    }

// default shop address is Ba Dinh, Ha Noi
    const shipmentList = await getShipmentList(shopId, shopDistrictId, shipmentDistrictId);

// create shipment option for select
    shipmentList.forEach((shipment) => {
        let serviceType;

        switch (shipment.service_type_id) {
            case 1:
                serviceType = 'Nhanh';
                break;
            case 2:
                serviceType = 'Ti??u chu???n';
                break;
            case 3:
                serviceType = 'Ti???t ki???m';
                break;
            default:
                serviceType = 'Kh??ng c?? d???ch v??? kh??? thi';
        }
        const optionEle = new Option(serviceType, shipment.service_type_id);
        shipmentTypeSelectEle.add(optionEle);
    });

// update cost on shipment change
    shipmentTypeSelectEle.addEventListener('change', updateSelectedShipmentCost);

})();

// get order item info
(async function () {
    const selectedItemJSON = sessionStorage.getItem('selectedItem');

    if (selectedItemJSON == null) {
        window.location.pathname = "/g11/cart";
        return;
    }

    const selectedItemObject = JSON.parse(selectedItemJSON);
    const selectedItemIdArray = Object.keys(selectedItemObject);

// get item info
    const getOrderItemInfo = async () => {
        const response = await fetch(`http://localhost:8080/g11/checkout/api/order-item-info?orderitemid=${selectedItemIdArray.toString()}`, {
            method: "GET"
        });

        const data = await response.text();
        if (data) {
            const dataTokens = data.split(';');

            if (dataTokens[0] === '200') {
                return dataTokens[1];
            }
        }

        return null;
    }

    const responseData = await getOrderItemInfo();
    const orderItemInfoArray = JSON.parse(jsonEscape(responseData));

    let totalItemCost = 0;
    const orderItemContainer = document.getElementById('order-item');

//    insert item info
    orderItemInfoArray.forEach((orderItemInfo, index) => {
        const quantity = selectedItemObject[orderItemInfo.id];
        const discountedPrice = orderItemInfo.price - orderItemInfo.price * (orderItemInfo.discount / 100);
        totalItemCost += discountedPrice * quantity;

        orderItemContainer.insertAdjacentHTML('beforeend', `
            <li class="d-flex align-items-center justify-content-between">
                <div class="item-general-info me-3">
                      <img class="item-img me-1" src="${orderItemInfo.image}" />
                      <span class="item-name">${orderItemInfo.name}</span>
               </div>
               <div class="item-price d-flex align-items-center">
                       ${new Intl.NumberFormat().format(discountedPrice)}
                       <sup>??</sup>
                    <div class="item-multiplier ms-1">
                            x${quantity}
                   </div>
              </div>
            </li>
        `);
    });

// update total cost
    totalPriceEle.value = totalItemCost;
    totalPriceEle.innerHTML = `${new Intl.NumberFormat().format(totalItemCost)}<sup>??</sup>`;
})();


// checkout
(function () {
    const payBtnEle = document.getElementById('pay-btn');

    const checkRequiredFieldFilled = () => {

    }

    payBtnEle.addEventListener('click', async () => {
        const selectedItemJSON = sessionStorage.getItem('selectedItem');

        if (selectedItemJSON == null) {
            window.location.pathname = "/g11/cart";
            return;
        }

        if (!customerAddressFilled) {
            Swal.fire({
                icon: 'warning',
                title: 'Th??ng tin nh???n h??ng c??n thi???u',
                showCancelButton: false,
                showConfirmButton: true,
                confirmButtonText: '<a href="/g11/user/account/profile" class="text-decoration-none text-white">??i???n th??ng tin nh???n h??ng</a>',
                confirmButtonColor: '#0d6efd'
            });
        }

        // check shipment type selected
        const selectedShipmentType = document.getElementById('shipment-type-select').value;
        if (selectedShipmentType === null || selectedShipmentType === '') {
            document.querySelector('.shipment-type-msg').innerText = "Vui l??ng ch???n h??nh th???c v???n chuy???n";
            return;
        }

        // check payment type selected
        const selectedPaymentTypeEle = document.querySelector('input[name="payment-type"]:checked');
        if (selectedPaymentTypeEle == null) {
            document.querySelector('.payment-type-msg').innerText = "Vui l??ng ch???n ph????ng th???c thanh to??n";
            return;
        }

        const selectedPaymentType = selectedPaymentTypeEle.value;
        const shipmentCost = document.getElementById('shipping-cost').value;

        const response = await fetch('http://localhost:8080/g11/customer/order/create', {
            method: 'POST',
            body: new URLSearchParams({
                selectedItemJSON,
                selectedPaymentType,
                selectedShipmentType,
                shipmentCost
            })
        });

        const data = await response.text();
        if (data) {
            const dataTokens = data.split(';');

            if (dataTokens[0] === '201') {
                sessionStorage.removeItem('selectedItem');

                const orderId = dataTokens[1];

                Swal.fire({
                    icon: 'success',
                    title: 'T???o ????n h??ng th??nh c??ng',
                    allowOutsideClick: false,
                    confirmButtonText: `<a href="/g11/customer/order?orderid=${orderId}" class="text-decoration-none text-white">Xem tr???ng th??i ????n h??ng</a>`,
                    footer: '<a href="/g11/home" class="text-decoration-none fs-6">Ti???p t???c mua h??ng</a>'
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'T???o ????n h??ng th???t b???i, vui l??ng th??? l???i sau',
                    allowOutsideClick: false,
                    showCancelButton: false,
                    showConfirmButton: true,
                });
            }
        }
    })
})();
