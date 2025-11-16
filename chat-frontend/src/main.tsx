
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { AuthProvider } from './context/AuthContext.tsx'
import { ChatProvider } from './context/ChatContext.tsx'

createRoot(document.getElementById('root')!).render(
  <AuthProvider>
    <ChatProvider>
      <App />
    </ChatProvider>
  </AuthProvider>,
)
