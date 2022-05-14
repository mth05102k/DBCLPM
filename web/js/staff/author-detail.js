/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// delete author
(function () {
    const authorId = document.getElementById('author-id').value;
    const bookId = document.getElementById('book-id').value;

    const deleteAuthor = async () => {
        console.log('delete')
        const response = await fetch('http://localhost:8080/g11/staff/book/author/delete', {
            method: 'POST',
            body: new URLSearchParams({
                authorid: authorId,
                bookid: bookId
            })
        });

        const data = await response.text();
        if (data) {
            const dataTokens = data.split(';');
            if (dataTokens[0] === '204') {
                setTimeout(() => {
                    window.location.href = dataTokens[1];
                }, 2200);

                Swal.fire({
                    icon: 'success',
                    title: 'Xóa thông tin tác giả thành công',
                    timer: 2000,
                    showCancelButton: false,
                    showConfirmButton: false,
                });

                return;
            }
        }

        Swal.fire({
            icon: 'success',
            title: 'Xóa thông tin tác giả thất bại',
            showCancelButton: false,
            showConfirmButton: true,
        });
    }

    const deleteBtn = document.getElementById('delete-btn');
    deleteBtn.addEventListener('click', deleteAuthor);
})();