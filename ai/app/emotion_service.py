from transformers import pipeline
# pipeline: modo per usare un modello di AI senza dover gestire tokenizer, tensori, ec...



class EmotionService:
    
    EMOTION_VALUES = {
        "anger": 0.05,
        "contempt": 0.10,
        "disgust": 0.10,
        "fear": 0.15,
        "sadness": 0.10,
        "frustration": 0.20,

        "neutral": 0.50,

        "surprise": 0.60,
        "gratitude": 0.85,
        "love": 0.95,
        "joy": 1.00
    }
    
    
    # Il modello viene caricato una sola volta
    classifier = pipeline(
        "sentiment-analysis",
        #model="nlptown/bert-base-multilingual-uncased-sentiment"
        model="tabularisai/multilingual-emotion-classification", # modello multilingua 
        top_k=None # restituisce i punteggi di tutte le categorie/emozioni, non solo la migliore
    )
    
    
    @staticmethod
    def analyze_emotion(text: str):
        
        results = EmotionService.classifier(text)[0]
        emotions = {}
        
        for prediction in results:
            emotion = prediction["label"]
            confidence = prediction["score"]
            
            emotions[emotion] = round(confidence, 4)
        
        mood_score = EmotionService.calculate_mood_score(emotions)
        summary = EmotionService.generate_summary(emotions)
        
        return {
            "moodScore": mood_score,
            "summary": summary,
            "jsonResult": {
                "emotions": emotions
            }
        }
    
    
    # -> float la funzione dovrebbe restituire un numero decimale 
    @staticmethod
    def calculate_mood_score(emotions: dict) -> float:
       
        weighted_sum = 0.0
        #total_confidence = sum(emotions.values())
        total_confidence = 0.0
        
        for emotion, confidence in emotions.items():
            value = EmotionService.EMOTION_VALUES.get(emotion, 0.5)
            weighted_sum += value * confidence

        if total_confidence == 0:
            return 0.5

        # Media pesata delle emozioni
        score = weighted_sum / total_confidence

        # Mantiene il risultato nell'intervallo[0,1]
        
        
        return round(max(0.0, min(1.0, score)),2)
        
    
    @staticmethod
    def generate_summary(emotions: dict) -> str:
        strongest_emotion = max(emotions, key=emotions.get)
        confidence = emotions[strongest_emotion]
        
        # Se nessuna emozione è sufficientemente dominante,
        # consideriamo lo stato emotivo come misto
        if confidence < 0.40:
            return "The text conveys a mixed emotional state"
        
        return f"The text predominantly conveys {strongest_emotion} ({confidence:.0%} confidence)"