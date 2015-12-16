-- sau khi chay hibernate

ALTER TABLE `nganh_monhoc`
ADD PRIMARY KEY (`Nganh_id`, `dsMonHoc_id`);

ALTER TABLE `account` ADD FOREIGN KEY (`khoaID`) REFERENCES `khoa` (`id`) ON DELETE NO ACTION;

ALTER TABLE `account` ADD FOREIGN KEY (`nganhID`) REFERENCES `nganh` (`id`) ON DELETE NO ACTION;

CREATE TABLE `xephang` (
`idXepHang`  bigint NOT NULL AUTO_INCREMENT ,
`idMonHoc`  bigint NULL ,
`idAccount`  bigint NULL ,
`tenAcc`  varchar(255) NULL ,
`doKho`  int NULL ,
`diem`  double NULL ,
PRIMARY KEY (`idXepHang`)
)
;

ALTER TABLE `xephang` ADD FOREIGN KEY (`idMonHoc`) REFERENCES `monhoc` (`id`);

ALTER TABLE `xephang` ADD FOREIGN KEY (`idAccount`) REFERENCES `account` (`id`);

CREATE TABLE `message` (
`id`  bigint NOT NULL AUTO_INCREMENT ,
`message`  text NULL ,
PRIMARY KEY (`id`)
)
;

