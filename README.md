# Omega_Test
Programming ability task for Omega

Projekt kojeg je potrebno uvesti u neki IDE.
Provo je potrebno lokalno pokrenut postgres bazu.
Napravit build projekta te sa "mvn clean install" napravit artifakt za docker.

Nakon toga je potrebno izvršit:
"docker compose build", te
"docker compose up"

GET: http://localhost:8080/api/ugovor => endpoint za dohvat svih ugovora

GET: http://localhost:8080/api/ugovor?status=KREIRANO&kupac=Marko => endpoint za dohvat svih ugovora te filtrairanje

GET: http://localhost:8080/api/ugovor/getUgovorById?id=3 => endpoint za dohvat ugovora i povezanih artikala

GET: http://localhost:8080/api/ugovor/getUgovorArtiklsById?id=3 => endpoint za dohvat ugovor artikla

POST: http://localhost:8080/api/ugovor/dodajUgovor => endpoint za kriranje novog ugovora
req_body:
{"kupac":"BMX21", "broj_ugovora":"21", "datum_akontacije":"2024", "rok_isporuke":"2023","status":"KREIRANO","artikls":[{"naziv":"Artikl_6","dobavljac":"SD","status":"KREIRANO"},{"naziv":"Artikl_7","dobavljac":"SD","status":"KREIRANO"}]}

 PUT: http://localhost:8080/api/ugovor/izmjeniUgovor => endpoint za izmjenu ugovora
req_body:

{"id":2,"kupac":"BMX21", "broj_ugovora":"21", "datum_akontacije":"2024", "rok_isporuke":"2023","status":"KREIRANO"}

DELETE: http://localhost:8080/api/ugovor/izbrisiUgovor?id=3 => endpoint za brisanje ugovora.
