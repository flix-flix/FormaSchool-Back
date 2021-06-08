FROM mongo

COPY db/import.sh /docker-entrypoint-initdb.d/import.sh
COPY db/ /db

# RUN mongoimport --db forma --collection user --file user2.json

# CMD ["mongod", "--config", "/etc/mongodb.conf", "--smallfiles"]
