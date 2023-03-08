package com.sparta.crudassignment.entity;

//여기서 그냥 클래스가 아니라 enum을 사용하는 이유는?
//enum의 특성을 알아야 함.
//일반적인 클래스였다면, 사용자가 입력한 값이 권한 중 하나인지 검증하는 로직을 작성해야 함.
// enum의 경우 정의된 권한 중 하나인지 검증하기만 하면 됨. 수가 2개로 정해져있으므로 == user 이거나 admin 이므로
// 또 메서드를 가질 수 있다는 장점도 있음
// 결론적으로 상수를 다룰 때는 enum을 사용하는게 좋음
public enum UserRoleEnum {
    USER,  // 사용자 권한
    ADMIN  // 관리자 권한
}