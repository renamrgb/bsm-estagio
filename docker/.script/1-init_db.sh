#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE academia;
	CREATE USER renam WITH PASSWORD 'renam';
	GRANT ALL ON DATABASE academia TO renam;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "academia" <<-EOSQL
  grant usage on schema public to renam;
  grant all privileges on schema public to renam;
EOSQL