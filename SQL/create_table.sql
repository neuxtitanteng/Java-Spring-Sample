
create table otp_history(
    history_id int not null identity(1,1),
    otp_number int not null,
    email nvarchar(500) not null,
    tel nvarchar(500) not null,
    create_time datetime not null,
    primary key(history_id)
);

