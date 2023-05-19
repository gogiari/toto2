
// -----------------------------------------------------------------------------------------start home js
let target = document.querySelector("#dynamic");
let stringArr = ["Let's Soccer. . . . .","Let's basketball . . . . .","Let's baseball . . . . . ",];

let selectString = stringArr[Math.floor(Math.random()*stringArr.length)];
let selectStringArr = selectString.split("");


function randomString(){
    
    let target = document.querySelector("#dynamic");
    let stringArr = ["Let's Soccer . . . . . ","Let's basketball . . . . . ","Let's baseball . . . . . ",];
    let selectString = stringArr[Math.floor(Math.random()*stringArr.length)];
    let selectStringArr = selectString.split("");
    
    return selectStringArr;
}

//타이핑 리셋
function resetTyping(){
  target.textContent = "";
  dynamic (randomString());
}


//한글자씩 텍스트 출력 함수
function dynamic(randomArr){
    if(randomArr.length > 0){
        target.textContent += randomArr.shift();
        setTimeout(function(){
            dynamic(randomArr);
        },100);
    }else{
        setTimeout(resetTyping, 3000);
    }
}

dynamic (randomString());

function blink(){
    
    target.classList.toggle("active");
    
}
setInterval(blink, 500);


const logregBox = document.querySelector('.logreg-box');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');

registerLink.addEventListener('click', ()=> {
    logregBox.classList.add('active');
});

loginLink.addEventListener('click', ()=> {
    logregBox.classList.remove('active');
});

