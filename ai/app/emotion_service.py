from transformers import pipeline

# analyze_emotion 
# 1. analizza il testo
# 2. identifica le emozioni
# 3. calcola il mood score 
# 4. genera il summary 
# 5. costruisce il risultato



class EmotionService:
    
    # Il modello viene caricato una sola volta
    classifier = pipeline(
        "sentiment-analysis",
        model="nlptown/bert-base-multilingual-uncased-sentiment"
    )
    
    
    @staticmethod
    def analyze_emotion(text: str):
        
        result = EmotionService.classifier(text)
        prediction = result[0]
        
        label = prediction["label"]
        confidence = prediction["score"]
        
        mood_score = EmotionService.calculate_mood_score(label)
        summary = EmotionService.generate_summary(label, confidence)
        
        return {
            "moodScore": mood_score,
            "summary": summary,
            "jsonResult": {
                "sentiment": label,
                "confidence": confidence
            }
        }
    
    
    # -> float la funzione dovrebbe restituire un numero decimale 
    @staticmethod
    def calculate_mood_score(label: str) -> float:
        stars = int(label[0])
        
        return (stars - 1) / 4
    
    @staticmethod
    def generate_summary(label: str, confidence: float) -> str:
        stars = int(label[0])
        
        if stars == 1:
            return "The text conveys a very negative mood"

        if stars == 2:
            return "The text predominantly conveys a negative mood"

        if stars == 3:
            return "The text conveys a neutral mood"

        if stars == 4:
            return "The text conveys a positive mood"

        return "The text conveys a very positive mood"
        