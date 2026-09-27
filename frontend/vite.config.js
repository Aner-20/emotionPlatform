import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // Necessario per Docker
    port: 5173,
    watch: {
      usePolling: true // Fondamentale per vedere le modifiche apportate
    }
  }
})
