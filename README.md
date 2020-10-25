# HTML Parser

This example shows how to create an [Java](https://www.oracle.com/ru/java/technologies/) application 
that downloads the html code from a link via [Commomns-io](https://commons.apache.org/proper/commons-io/) and [Jsoup](https://jsoup.org/)
, saves it to the hard drive
, then creates word statistics 
and saves it to the [Postgres](https://www.postgresql.org/) database.


* [Getting Started](#getting-started)
* [Development environment](#development-environment)
* [Used technologies](#used-technologies)

## Getting Started

First of all, you have to clone my repo. Just type it in terminal:
```bash
git clone https://github.com/egrva/html-parser.git
```
After that, you should go to the folder `jar-file`
```bash
cd jar-file
```
So, to run application you should choose any link, for example www.google.com . this link serves as an argument.
```bash
java -jar html-parser-1.0-SNAPSHOT.jar https://www.example.com
```
Jar file does not contain saving to database, but saving is described in code.
Downloaded htmlfiles you can find in `storage` directory.
## Development environment
If you want launch this app with saving in database, you should:
* Create table in database
```postgresql
create table words
(
	word varchar,
	num_of_occur int
);
```
* Change info in `db.properties` file

## Used technologies

* [Maven](https://maven.apache.org/) to buid project
* [Java](https://www.oracle.com/ru/java/technologies/) language
* [slf4j](http://www.slf4j.org/) and [Logback](http://logback.qos.ch/) for logging
* [jsoup](https://jsoup.org/) for work with html files
* [Commons-io](https://commons.apache.org/proper/commons-io/) to download files
* [PostgreSQL](https://www.postgresql.org/) as db
* [JUnit](https://junit.org/junit4/) for testing
