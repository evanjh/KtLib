FROM debian:buster

RUN apt update && \
apt install -y locales openssl zlib1g libc++-dev default-jre-headless && \
apt clean && \
echo "en_US.UTF-8 UTF-8" >> /etc/locale.gen && \
locale-gen

ENV LC_ALL en_US.UTF8

ADD libtdjni.so /usr/lib