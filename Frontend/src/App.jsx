import React, { useState } from 'react';
import { ThemeProvider } from './contexts/ThemeContext';
import Header from './components/Header';
import Hero from './components/Hero';
import UploadSection from './components/UploadSection';
import SnippetSection from './components/SnippetSection';
import SecurityBadge from './components/SecurityBadge';
import AnalyzeButton from './components/AnalyzeButton';
import Features from './components/Features';

function App() {
  const [code, setCode] = useState('');

  const handleFileLoad = (fileContent, fileName) => {
    setCode(fileContent);
    alert(`File "${fileName}" loaded successfully!`);
  };

  const handleAnalyze = () => {
    if (code.trim()) {
      alert('Analyzing your code... This would trigger the AI analysis in production!');
    } else {
      alert('Please paste some code or upload a file first.');
    }
  };

  return (
    <ThemeProvider>
      <div className="min-h-screen bg-gradient-to-br from-gray-50 to-gray-100 
                      dark:from-gray-950 dark:to-gray-900 transition-colors duration-300">
        <div className="max-w-7xl mx-auto px-4 py-5">
          <Header />
          <Hero />
          
          {/* Two columns side by side */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-8 mb-10">
            <UploadSection onFileLoad={handleFileLoad} />
            <SnippetSection code={code} onCodeChange={setCode} />
          </div>

          <SecurityBadge />
          <AnalyzeButton onClick={handleAnalyze} disabled={!code.trim()} />
          
          <div className="h-10"></div>
          <Features />
        </div>
      </div>
    </ThemeProvider>
  );
}

export default App;