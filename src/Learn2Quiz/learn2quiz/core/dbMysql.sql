CREATE TABLE tblUser (
	idUser VARCHAR(255) NOT NULL,
	dtBenutzername VARCHAR(50) NOT NULL,
	dtPasswort VARCHAR(50) NOT NULL,
	dtEmail VARCHAR(50) NOT NULL,
	dtPunktzahl INT,
	PRIMARY KEY (idUser)
);

CREATE TABLE tblGruppe (
	idGruppe VARCHAR(255) NOT NULL,
	dtTitel VARCHAR(50) NOT NULL,
	dtBeschreibung VARCHAR(255),
	dtTimestamp TIMESTAMP NOT NULL,
	fiErsteller VARCHAR(255) NOT NULL,
	PRIMARY KEY (idGruppe),
	FOREIGN KEY (fiErsteller) REFERENCES tblUser(idUser)
);

CREATE TABLE tblIstTeil (
	fiUser VARCHAR(255) NOT NULL,
	fiGruppe VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	PRIMARY KEY (fiUser, fiGruppe),
	FOREIGN KEY (fiUser) REFERENCES tblUser(idUser),
	FOREIGN KEY (fiGruppe) REFERENCES tblGruppe(idGruppe)
);

CREATE TABLE tblEinladung (
	idEinladung VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	fiAbsender VARCHAR(255) NOT NULL,
	fiGruppe VARCHAR(255) NOT NULL,
	PRIMARY KEY (idEinladung),
	FOREIGN KEY (fiAbsender) REFERENCES tblUser(idUser),
	FOREIGN KEY (fiGruppe) REFERENCES tblGruppe(idGruppe)
);

CREATE TABLE tblErhaelt (
	fiEinladung VARCHAR(255) NOT NULL,
	fiEmpfänger VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	PRIMARY KEY (fiEinladung, fiEmpfänger),
	FOREIGN KEY (fiEmpfänger) REFERENCES tblUser(idUser),
	FOREIGN KEY (fiEinladung) REFERENCES tblEinladung(idEinladung)
);

CREATE TABLE tblKartenset (
	idKartenset VARCHAR(255) NOT NULL,
	dtTitel VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	fiErsteller VARCHAR(255) NOT NULL,
	fiGruppe VARCHAR(255) NOT NULL,
	PRIMARY KEY (idKartenset),
	FOREIGN KEY (fiErsteller) REFERENCES tblUser(idUser),
	FOREIGN KEY (fiGruppe) REFERENCES tblGruppe(idGruppe)
);

CREATE TABLE tblKarteikarte (
	idKarteikarte VARCHAR(255) NOT NULL,
	dtFrage VARCHAR(255) NOT NULL,
	dtErsteAlt VARCHAR(255) NOT NULL,
	dtZweiteAlt VARCHAR(255) NOT NULL,
	dtDritteAlt VARCHAR(255) NOT NULL,
	dtErsterTip VARCHAR(50) NOT NULL,
	dtZweiterTip VARCHAR(50) NOT NULL,
	dtDritterTip VARCHAR(50) NOT NULL,
	dtSchwierigkeit VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	fiErsteller VARCHAR(255) NOT NULL,
	fiKartenset VARCHAR(255) NOT NULL,
	PRIMARY KEY (idKarteikarte),
	FOREIGN KEY (fiErsteller) REFERENCES tblUser(idUser),
	FOREIGN KEY (fiKartenset) REFERENCES tblKartenset(idKartenset)
);

CREATE TABLE tblKommentar (
	idKommentar VARCHAR(255) NOT NULL,
	dtInhalt VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	fiErsteller VARCHAR(255) NOT NULL,
	fiKarteikarte VARCHAR(255) NOT NULL,
	PRIMARY KEY (idKommentar),
	FOREIGN KEY (fiErsteller) REFERENCES tblUser(idUser),
	FOREIGN KEY (fiKarteikarte) REFERENCES tblKarteikarte(idKarteikarte)
);

CREATE TABLE tblSpielsession (
	idSpielsession VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	fiKartenset VARCHAR(255) NOT NULL,
	PRIMARY KEY (idSpielsession),
	FOREIGN KEY (fiKartenset) REFERENCES tblKartenset(idKartenset)
);

CREATE TABLE tblSpielt (
	fiUser VARCHAR(255) NOT NULL,
	fiSpielsession VARCHAR(255) NOT NULL,
	dtPunkte VARCHAR(255) NOT NULL,
	dtTimestamp TIMESTAMP NOT NULL,
	PRIMARY KEY (fiUser, fiSpielsession),
	FOREIGN KEY (fiUser) REFERENCES tblUser(idUser),
	FOREIGN KEY (fiSpielsession) REFERENCES tblSpielsession(idSpielsession)
);
