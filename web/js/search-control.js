/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    const searchInput = document.getElementById('search-input');
    const searchControlBtn = document.getElementById('search-control-btn');

    searchInput.addEventListener('keyup', async function (e) {
        if (e.key == "Enter" || e.keyCode == 13) {
            search();
        }
    })

    searchControlBtn.addEventListener('click', async function () {
        search();
    })

    function search() {
        const value = searchInput.value;

        if (value === null && value === "")
            return;

        const searchParams = new URLSearchParams(window.location.search);
        searchParams.set("q", value);
        window.location.search = searchParams.toString();
    }
})();