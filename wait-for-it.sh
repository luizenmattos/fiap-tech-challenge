#!/usr/bin/env sh
# wait-for-it.sh - Aguarda por um host:porta estar disponível antes de executar o comando

set -e

host="$1"
shift
port="$1"
shift

until nc -z "$host" "$port"; do
  echo "Aguardando $host:$port..."
  sleep 2
done

exec "$@"
