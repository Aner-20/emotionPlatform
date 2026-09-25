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
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]