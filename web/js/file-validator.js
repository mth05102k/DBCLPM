/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function customFileValidator(file, sizeConstraint, typeConstraint) {
    const sizeInMB = file.size / (1024 * 1024);
    if (sizeInMB > sizeConstraint) {
        window.alert('Maximum file size is 1 MB');
        return false;
    }
    
    if(typeConstraint && file.type !== typeConstraint) {
        window.alert('File type must be ' + typeConstraint);
        return false;
    }
    
    return true;
}