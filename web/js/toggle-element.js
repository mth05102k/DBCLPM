/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function elementToggler(toggledElementId, togglerElementId, className1, className2) {
    const toggledElement = document.getElementById(toggledElementId);
    const togglerElement = document.getElementById(togglerElementId);
    const iconElement = document.querySelector(`#${togglerElementId} + label > i`);

    function toggleClassName(cName1, cName2) {
        iconElement.classList.add(cName2);
        iconElement.classList.remove(cName1);
    }

    function toggleClassNameAndType(cName1, cName2, type) {
        toggleClassName(cName1, cName2);
        toggledElement.type = type;
    }

    const desiredTogglerFunc = toggledElement ? toggleClassNameAndType : toggleClassName;

    togglerElement.addEventListener('change', () => {
        if (togglerElement.checked) {
            desiredTogglerFunc(className1, className2, 'text');
        } else {
            desiredTogglerFunc(className2, className1, 'password');
        }
    });
}
