# Phone Number Formatted (0.0.2)

## How to work

![](https://lh3.googleusercontent.com/J5bwkt6FyNG7FJwaDpqCIW89g1tElRclfDbj52r1sjLMgzA6Krr3DYpygX5TynPgp77t_NtkdNwISOvrWg_gf6gN80K50np6k-LRGkGsvZ5FduzlzoBxAut2NTirzMp-UGCQYnW4uGEidT_Wt2U65_pci8mACEAz_XaF4tottNkfhiZlGpNORYc1AvFBaZqVw9QHH2E8SCb-8nJw4LeBpzhfPlpYylUgvH-PGm8nvjRAbdV724CCRIvmZ2IF5_JHfCzM1zkfRuDWvacbsPd2IitJthK5ohLRwsjCXdjF2dFjsD3rPnGR_5-IUDrC8C3UUgrz1QeE8DD3wyTzUPtAfVpkQ7KJfN6upIxK6DBTAzOv_ZpGoYU-wppxvPSLSE055vY_dFwFiynpaidrCEWIDJT1yqIBVOK3-1ETvgfdzxueFQaNRbBTqRnrVv4QWNzBYWnlyYwLbUk6PEf7XjfZxM9HgK-tAZ3Rc0r1kEl1EQwyaRKGmml4waAbWOcBr6tsXMZQXEEJfI-3WKxASxzkj3LuRjmpGt9A5nTyS7caQ0qPdZILKiZRzIAWLM6qUNR5Nqjd74cXcPie1lt3mc0UMFcrPECcE7t2C5gd6jLLiPK9pnG7JocpFMK_vK7HE7Tmmwcm0N2d4pAA_3W7xH9J84-JPaaGWOWPsiVGh8zCNwhKoCl9JIyA8JDNx6ZH=w320-h661-no?authuser=0)

## How to use

### in .xml file

```
    <com.thanh_nguyen.phonenumberformated.PhoneNumberFormattedEditText
        android:id="@+id/edtPhone"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


```

### in .java/.kt file

Set split character 
`edtPhone.setSplitChar('-')`

Set listener on typing
`edtPhone.setPhoneValidateListener(this)`

## How to implement

```
...
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
...
```

`implementation 'com.github.thanhnh98:phone_number_formatted:{newest-version}'`


