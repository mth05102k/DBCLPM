/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function toggleInputEditable(toggledElementId, togglerElementId, className1, className2, labelContent1, labelContent2) {
    const toggledElement = document.getElementById(toggledElementId);
    const togglerElement = document.getElementById(togglerElementId);
    const labelElement = document.querySelector(`#${togglerElementId} + label`);

    function toggleClassName(cName1, cName2, labelContent) {
        toggledElement.classList.add(cName2);
        toggledElement.classList.remove(cName1);
        toggledElement.toggleAttribute('readonly');

        labelElement.innerText = labelContent;
    }

    togglerElement.addEventListener('change', () => {
        if (togglerElement.checked) {
            toggleClassName(className1, className2, labelContent2);
        } else {
            toggleClassName(className2, className1, labelContent1);
        }
    });
}

function toggleEditable(unEditableEleId, editableEleId, displayClassName, hiddenClassName, togglerCheckboxId, unEditableLabel, editableLabel) {
    const unEditableEle = document.getElementById(unEditableEleId);
    const editableEle = document.getElementById(editableEleId);
    const togglerCheckbox = document.getElementById(togglerCheckboxId);
    const labelElement = document.querySelector(`#${togglerCheckboxId} + label`);
    
    function toggleDisplay(displayEle, hiddenEle, label) {
        displayEle.classList.add(displayClassName);
        displayEle.classList.remove(hiddenClassName);

        hiddenEle.classList.add(hiddenClassName);
        hiddenEle.classList.remove(displayClassName);


        labelElement.innerText = label;
    }

    togglerCheckbox.addEventListener('change', () => {
        if (togglerCheckbox.checked) {
            toggleDisplay(editableEle, unEditableEle, editableLabel);
        } else {
            toggleDisplay(unEditableEle, editableEle, unEditableLabel);
        }
    });
}
