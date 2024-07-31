FROM redis
RUN apt update && \
    apt install dnsutils -y
ADD redis/connect-redis-cluster.sh /usr/local/bin/connect-redis-cluster
RUN chmod 755 /usr/local/bin/connect-redis-cluster
ENTRYPOINT ["connect-redis-cluster"]
