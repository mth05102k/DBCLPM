/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(async function () {
    const districtSelectEle = document.getElementById('district-select');
    const citySelectEle = document.getElementById('city-select');

    const getUserAddress = async () => {
        const response = await fetch('http://localhost:8080/g11/user/api/get-address', {
            method: 'GET'
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

    const getDistrictInfo = async (provinceId) => {
        const response = await fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${provinceId}`, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Token': '92ba9c57-658c-11ec-9054-0a1729325323'
            },
        });

        const data = await response.json();
        if (data.code === 200) {
            const districtList = data.data;

            districtList.forEach((district) => {
                districtSelectEle.add(new Option(
                        district.DistrictName,
                        `${district.DistrictID};${district.DistrictName}`
                        ));
            });
        }
    }

    const getCityInfo = async () => {
        const response = await fetch('https://online-gateway.ghn.vn/shiip/public-api/master-data/province', {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Token': '92ba9c57-658c-11ec-9054-0a1729325323'
            }
        });

        const data = await response.json();
//        if (data.code === 200) {
//            const cityList = data.data;
//
//            cityList.forEach((city) => {
//                citySelectEle.add(new Option(
//                        city.ProvinceName,
//                        city.ProvinceID
//                        ));
//            });
//        }
        if (data.code === 200) {
            return data.data;
        }

        return null;
    }

    const removeAllOption = (selectEle) => {
        if (selectEle === null)
            return;

        for (let i = selectEle.options.length - 1; i >= 0; i--) {
            selectEle.remove(i);
        }
    }

    Promise.all([getCityInfo(), getUserAddress()]).then(([cityInfoList, userAddressInfo]) => {
        cityInfoList.forEach((city) => {
            const optionEle = new Option(
                    city.ProvinceName,
                    `${city.ProvinceID};${city.ProvinceName}`
                    )
            citySelectEle.add(optionEle);
        });
    });

    citySelectEle.addEventListener('change', (e) => {
        removeAllOption(districtSelectEle);
        getDistrictInfo(e.target.value);
    });
})()
