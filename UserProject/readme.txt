this is a simple user information app.

hibernate.cfg.xml has to be updated before you run this app.(user,key)

this is the table creator script :

CREATE TABLE USER (
   id int(11) NOT NULL,
   name varchar(45) NOT NULL,
   surname varchar(45) NOT NULL,
   phone varchar(45)
   PRIMARY KEY (`id`)
);

and last but not least, there is a bug on the edit dialog.not sure if it's one of the primefaces' bugs or it's my fault.