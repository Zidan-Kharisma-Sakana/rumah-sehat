# Tugas Akhir
## Kelompok 59 - RumahSehat
## Anggota Kelompok
* **Zidan Kharisma Adidarma** - *2006463881* - *A*
* **Brev William Fiden Saragih** - *2006596926* - *A*
* **Rayyan Azka Ihsanuddin** - *2006534764* - *A*
* **Farah Sausan Aulita Adinata** - *2006597140* - *A*
* **Alifah Azka Nisrina** - *1906353662* - *A*

---
**Kontrak Tahap 1 Tugas Akhir**

| NPM | Nama Lengkap | Fitur yang akan diselesaikan  |
| ----------| --- | ---------- | 
| 2006463881 | Zidan Kharisma Adidarma | - |
| 2006596926 | Brev William Fiden Saragih | - |
| 2006534764 | Rayyan Azka Ihsanuddin | - |
| 2006597140 | Farah Sausan Aulita Adinata | - |
| 1906353662 | Alifah Azka Nisrina | - |
---
**Kontrak Tahap 2 Tugas Akhir**

| NPM | Nama Lengkap | Fitur yang akan diselesaikan  |
| ----------| --- | ---------- | 
| 2006463881 | Zidan Kharisma Adidarma | 2, 3|
| 2006596926 | Brev William Fiden Saragih | 1, git |
| 2006534764 | Rayyan Azka Ihsanuddin | 4, 15 |
| 2006597140 | Farah Sausan Aulita Adinata | 8, 9 |
| 1906353662 | Alifah Azka Nisrina | 12, 13 |
---
**Kontrak Tahap 3 Tugas Akhir**

| NPM | Nama Lengkap | Fitur yang akan diselesaikan  |
| ----------| --- | ---------- | 
| 2006463881 | Zidan Kharisma Adidarma | 5, 19 |
| 2006596926 | Brev William Fiden Saragih | 14, 16 |
| 2006534764 | Rayyan Azka Ihsanuddin | 6, 7|
| 2006597140 | Farah Sausan Aulita Adinata | 10, 11 |
| 1906353662 | Alifah Azka Nisrina | 18, 17 |
---

# Dokumentasi REST API RumahSehat


Rest API untuk Tugas Akhir APAP 2022 \

Base URL: https://apap-059.cs.ui.ac.id/api/ \

API List: authenticateUser patientRegistration createAppointment allAppointment detailAppointment detailPrescription allDoctors retrieveListInvoice detailPrescription getPatientProfile  

---

## authenticateUser

Melakukan autententikasi user. Menerima input username dan password dari aplikasi.\
URL: https://apap-059.cs.ui.ac.id/api/auth/login \
Method: POST \
Header: Content-Type: application/json;charset=UTF-8

Body Param
```
{
    "username" : "pasien",
    "password" : "pasien"
}
```

**Success Response**
```
{
    "name": "pasien",
    "email": "pasien@gmail.com",
    "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNpZW4iLCJpYXQiOjE2NzA4MDgyODgsImV4cCI6MTY3MDg5NDY4OH0.IU4hvqyOXy81mLAxy79ZEeMNaz4FhRO_LM1K9S1rEz7YHUBCxYnQkl6fMeMFw1XVar9JF1CWN2FPd8MVsrI84g"
}
```

## patientRegistration

Melakukan registrasi user\
URL: https://apap-059.cs.ui.ac.id/api/auth/signup \
Method: POST \
Header: Content-Type: application/json;charset=UTF-8

Body Param
```
{
    "name" : "My Pasien",
    "username" : "myPasien",
    "password" : "myPasien",
    "email" : "myPasien@gmail.com",
    "age" : 20
}
```

**Success Response**
```
{
    "uuid": "8a85de708503fda3018503fdbdaa0000",
    "role": "PATIENT",
    "username": "myPasien",
    "email": "myPasien@gmail.com",
    "name": "My Pasien",
    "balance": 0,
    "age": 20
}
```

## getPatientProfile

Mengambil profile pasien.\
URL: https://apap-059.cs.ui.ac.id/api/user \
Method: POST \
Header:
- Content-Type: application/json;charset=UTF-8
- Authorization: Bearer {jwtToken}

**Success Response**
```
{
    "uuid": "8a85de708503e87d018503e911400003",
    "role": "PATIENT",
    "username": "pasien",
    "email": "pasien@gmail.com",
    "name": "Pasien",
    "balance": 0,
    "age": 10
}
```

## createAppointment

Menambahkan Appointment ke dalam RumahSehat. \
URL: https://apap-059.cs.ui.ac.id/api/appointment/add \
Method: POST 

Header:
- Content-Type: application/json;charset=UTF-8
- Authorization: Bearer {jwtToken}

Body Param
```
{
    "date": "2022-12-11",
    "hour": 11,
    "minute":30,
    "doctor_uuid": "8a85de708503e87d018503e910f40002"

}
```
**Success Response**
```
{
    "success": true,
    "message": "Sukses, Kode Appointment adalah APT-3"
}
```

## allAppointment

Mengambil daftar appointment pasien.\
URL: https://apap-059.cs.ui.ac.id/api/appointment/all \
Method: GET
Header:
- Content-Type: application/json;charset=UTF-8
- Authorization: Bearer {jwtToken}

**Success Response**
```
[
    {
        "code": "APT-1",
        "doctor_name": "Dokter",
        "isDone": "Belum selesai",
        "date": "2022-12-12",
        "start": "08:18:04",
        "end": "09:18:04"
    },
    {
        "code": "APT-2",
        "doctor_name": "Dokter",
        "isDone": "Belum selesai",
        "date": "2022-12-12",
        "start": "08:18:04",
        "end": "09:18:04"
    },
    {
        "code": "APT-3",
        "doctor_name": "Dokter",
        "isDone": "Belum selesai",
        "date": "2022-12-11",
        "start": "11:30",
        "end": "12:30"
    }
]
```

## detailAppointment

Melihat detail Appointment.\
URL: https://apap-059.cs.ui.ac.id/api/appointment/detail/{appointmentCode} \
Method: GET 

Header:
- Content-Type: application/json;charset=UTF-8
- Authorization: Bearer {jwtToken}

**Success Response**
```
{
    "code": 1,
    "status": "Belum Selesai",
    "waktu-awal": "12 December 2022 03:05",
    "nama-dokter": "Dokter",
    "nama-pasien": "Pasien",
    "nama-confirmer": "-",
    "list-drug": [
        {
            "quantity": 2,
            "nama": "Paracetamol"
        }
    ]
}
```

## allDoctors

Mengambil daftar Doctor.\
URL: https://apap-059.cs.ui.ac.id/api/appointment/doctor \
Method: GET

Header:
- Content-Type: application/json;charset=UTF-8
- Authorization: Bearer {jwtToken}

**Success Response**
```
[
    {
        "uuid": "8a85de708503e87d018503e910f40002",
        "role": "DOCTOR",
        "username": "dokter",
        "email": "dokter@gmail.com",
        "name": "Dokter",
        "fee": 20000
    }
]
```

## retrieveListInvoice

Mengambil daftar Invoice.\
URL: https://apap-059.cs.ui.ac.id/api/invoice/get-all/{username} \
Method: GET
Header:
- Content-Type: application/json;charset=UTF-8
- Authorization: Bearer {jwtToken}


**Success Response**
```
[
    {
        "code": "BILL-1",
        "dateIssued": "2022-12-12T08:18:04",
        "datePaid": "2022-12-12T08:18:04",
        "isPaid": true,
        "amount": 1000
    },
    {
        "code": "BILL-2",
        "dateIssued": "2022-12-12T08:18:04",
        "datePaid": "2022-12-12T08:18:04",
        "isPaid": true,
        "amount": 2000
    }
]
```


