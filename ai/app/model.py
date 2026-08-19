# Pydantic definisce e valida i dati che entrano ed escono dalle API
# Senza questa classe non si potrebbe creare un endpoint
from pydantic import BaseModel

class EmotionRequest(BaseModel):
    text: str
    
class EmotionResponse(BaseModel):
    moodScore: float
    summary: str
    jsonResult: dict