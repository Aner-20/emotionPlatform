# FastApi è il framework che si usa per creare gli endpoint HTTP
# Unicorn è il server che serve per ricevere ed eseguire le richieste HTTP

from fastapi import FastAPI
from model import EmotionRequest
from model import EmotionResponse
from emotion_service import EmotionService


app = FastAPI()

# response_model è il tipo di risposta

@app.post("/analyze", response_model=EmotionResponse)
def analyze(request: EmotionRequest):
    result = EmotionService.analyze_emotion(request.text)
    return result
    

