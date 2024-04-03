/*!
    * Start Bootstrap - SB Admin v7.0.4 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2021 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

// 회원가입 및 개인정보 수정 유효성 검사 모음

    /* 이메일 주소 유효성 검사 */
    function validateEmail(email) {
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailRegex.test(email);
    }

    /* 비밀번호 유효성 검사 */
    function validatePassword(password) {
        // 영문, 특수문자의 조합으로 8자 이상 확인
        const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).{8,}$/;
        return passwordRegex.test(password);
    }

    /* 이름 유효성 검사 */
    function validateName(name) {
        const nameRegex = /^[가-힣a-zA-Z]+$/;
        return nameRegex.test(name);
    }

    /* 닉네임 유효성 검사 */
    function validateNick(nickname) {
        const nickRegex = /^[가-힣a-zA-Z0-9]{2,7}$/;
        return nickRegex.test(nickname);
    }

    /* 생년월일 유효성 검사 */
    function validateBirth(birth){
        // yyyy-mm-dd 형식으로 생년월일을 검사하는 정규식
        const dateOfBirthRegex = /^\d{4}-\d{2}-\d{2}$/;
        return dateOfBirthRegex.test(birth);
    }

    /* 핸드폰번호 유효성 검사 */
    function validatePhone(phone) {
        const phoneRegex = /^(010)-\d{4}-\d{4}$/;
        return phoneRegex.test(phone);
    }

    /* 입력된 핸드폰 번호에 자동으로 하이픈을 추가하는 함수 */
    function addHyphen() {
      let phoneNumber = document.getElementById('inputPhone').value;
      // 숫자만 남기고 다른 문자 제거
      phoneNumber = phoneNumber.replace(/[^0-9]/g, '');

      let formattedPhoneNumber = '';
      if (phoneNumber.length < 4) {
        formattedPhoneNumber = phoneNumber;
      } else if (phoneNumber.length < 7) {
        formattedPhoneNumber = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3);
      } else if (phoneNumber.length < 11) {
        formattedPhoneNumber = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6);
      } else {
        formattedPhoneNumber = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7, 4);
      }

      // 입력 필드에 포맷된 번호 출력
      document.getElementById('inputPhone').value = formattedPhoneNumber;
    }

    /* 주소 찾기 api */
    function findAddress(){
        new daum.Postcode({
            oncomplete: function(data){
                let addr = ''; // 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if(data.userSelectedType === 'R'){ // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;;
                }else{ // 사용자가 지번 주소를 선택했을 경우('J')
                    addr = data.jibunAddress;
                }

                // 주소 정보를 해당 필드에 삽입
                document.getElementById("inputAddress").value = addr;
            }
        }).open({
            left: (window.innerWidth - 720) / 2,
            top: (window.innerHeight - 500) / 2
        });
    }

    /* 닉네임 중복검사 */
    function nicknameCheck(nickname){
        let checkBool = false;
        $.ajax({
            type: 'GET',
            url: `/checkNickname?nickname=${nickname.value}`,
            async: false,
            success: function(response){
                checkBool = response;
            },
            error: function(xhr,status,error) {
                console.error('회원정보 수정 중 오류가 발생했습니다.', error);
            }
        })
        return checkBool;
    }
