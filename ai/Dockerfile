# Ha solo una fase essendo un linguaggio compilato

FROM python:3.11-slim

WORKDIR /app

# Copia e installa le dipendenze per sfruttare la cache di Docker
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copia tutto il resto del codice Python
COPY . .

# Espone la porta su cui gira Uvicorn/FastAPI
EXPOSE 8000

# Avvia l'applicazione FastAPI tramite Uvicorn
# 0.0.0.0 Ascolta tutte le connessioni di rete provenienti dall'esterno del container
# 8000 Specifica la porta interna al container su cui il server Uvicorn accetterà le richieste HTTP
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]