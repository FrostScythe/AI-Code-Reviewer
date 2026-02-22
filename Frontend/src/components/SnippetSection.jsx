import { useRef } from 'react';

const SnippetSection = ({ code, onCodeChange }) => {
  const textareaRef = useRef(null);

  const handleClear = () => {
    onCodeChange('');
  };

  const handleCopy = () => {
    if (textareaRef.current) {
      textareaRef.current.select();
      navigator.clipboard.writeText(code);
      alert('Code copied to clipboard!');
    }
  };

  return (
    <div className="bg-white dark:bg-gray-900 rounded-xl p-8 shadow-lg transition-colors flex flex-col min-h-[500px]">
      <div className="flex justify-between items-center mb-5">
        <div className="flex items-center gap-2">
          <span className="text-xl">⚡</span>
          <h2 className="text-lg font-semibold text-gray-900 dark:text-white">Paste Snippet</h2>
        </div>
        <div className="flex gap-2">
          <button 
            onClick={handleClear}
            className="w-8 h-8 flex items-center justify-center bg-gray-100 dark:bg-gray-800 
                       rounded-md text-gray-600 dark:text-gray-400 hover:bg-gray-200 
                       dark:hover:bg-gray-700 transition-colors"
            title="Clear"
          >
            🗑️
          </button>
          <button 
            onClick={handleCopy}
            className="w-8 h-8 flex items-center justify-center bg-gray-100 dark:bg-gray-800 
                       rounded-md text-gray-600 dark:text-gray-400 hover:bg-gray-200 
                       dark:hover:bg-gray-700 transition-colors"
            title="Copy"
          >
            📋
          </button>
        </div>
      </div>

      <div className="flex-1 bg-[#1e1e1e] dark:bg-black rounded-xl overflow-hidden 
                      border-2 border-white flex flex-col">
        <div className="bg-[#2d2d2d] dark:bg-gray-900 px-4 py-3 flex items-center gap-2 border-b border-white">
          <div className="flex gap-1.5">
            <div className="w-3 h-3 rounded-full bg-red-500"></div>
            <div className="w-3 h-3 rounded-full bg-yellow-500"></div>
            <div className="w-3 h-3 rounded-full bg-green-500"></div>
          </div>
          <span className="text-xs text-gray-300 ml-2">snippet.js</span>
          <span className="text-xs text-gray-300 ml-auto">UTF-8</span>
        </div>
        
        <div className="p-5 flex-1">
          <textarea
            ref={textareaRef}
            value={code}
            onChange={(e) => onCodeChange(e.target.value)}
            placeholder="// Paste your code here..."
            className="w-full h-full bg-transparent text-gray-300 
                       font-mono text-sm resize-none outline-none"
            rows="10"
          />
        </div>
      </div>
    </div>
  );
};

export default SnippetSection;