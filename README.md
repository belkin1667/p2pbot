# L2L Hackathon Project

## Peer To Peer Bot Platform "Peers"

### Local Run

1) Create and run docker postgres image

- run `docker-compose -f ./utils/postgres.yml up -d` from project root directory

2) Setup profile

- Set Active Profiles Property to `local` in IDEA Run Configuration
- Or use `-Dspring.active.profiles=local` jvm option

### Running Tests

1) Create and run docker postgres image

- run `docker-compose -f ./utils/postgres.yml up -d` from project root directory
- TDB: use testcontainers for embedded postgres

2) Execute tests

### Production Run

- Use `-Dspring.active.profiles=production` jvm option
