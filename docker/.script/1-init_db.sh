#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE bsm;
	CREATE USER docker WITH PASSWORD 'docker';
	GRANT ALL ON DATABASE bsm TO docker;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "bsm" <<-EOSQL
  grant usage on schema public to docker;
  grant all privileges on schema public to docker;
EOSQL