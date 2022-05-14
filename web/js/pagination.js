/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    const prevPageBtn = document.querySelector('.prev-page-btn');
    const nextPageBtn = document.querySelector('.next-page-btn');
    const href = document.location.href;
// start index of current page query param
    const indexOfPageParam = Number(href.indexOf('page=') + 'page='.length);
    let currentPage = 1;
//    index of current page query param greater than -1
    if (indexOfPageParam >= 'page='.length) {
        const indexOfParamSeparator = href.indexOf('&', indexOfPageParam);
        if (indexOfParamSeparator != -1) {
            currentPage = Number(href.substring(indexOfPageParam, indexOfParamSeparator));
        } else {
            currentPage = Number(href.substring(indexOfPageParam));
        }
    }

    const currentPageItem = document.querySelector(`.pagination-list a[href="?page=${currentPage}"]`);
    if (currentPageItem) {
        currentPageItem.classList.add('active');
    }

    if (currentPage > 1) {
        prevPageBtn.addEventListener('click', () => {
            document.location.href = href.replace(`page=${currentPage}`, `page=${currentPage - 1}`);
        });
    }


    nextPageBtn.addEventListener('click', () => {
        if (indexOfPageParam >= 'page='.length) {
            document.location.href = href.replace(`page=${currentPage}`, `page=${currentPage + 1}`);
        } else {
            document.location.href = href + `?page=${currentPage + 1}`;
        }
    });
})();
