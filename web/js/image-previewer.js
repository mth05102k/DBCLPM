/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function imagePreviewer(inputElementId, imageContainerId, validator, constraint) {
    document.getElementById(inputElementId).addEventListener('change', function () {
        const [image] = this.files;
        if (image) {
            if (validator && !validator(image, ...constraint)) {
                return;
            }
            
            document.getElementById(imageContainerId).src = URL.createObjectURL(image);
        }
    });
}
