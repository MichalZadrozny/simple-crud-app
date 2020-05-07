const $mark = document.querySelector('#mark');
const $model = document.querySelector('#model');
const $color = document.querySelector('#color');
const $submitButton = document.querySelector('.submit-btn');
const $submitInfo = document.querySelector('.submit-info');
const $form = document.querySelector('form');

function isNotEmpty(){

    if($mark.value === '' || $model.value === '' || $color.value === ''){
        $submitInfo.classList.remove('hide');
        console.log("Submit button clicked");

    }else{
        $form.submit();
        $submitInfo.classList.add('hide');
    }
}

$submitButton.addEventListener("click",isNotEmpty);