import React, { useCallback, useState } from 'react';

const UploadSection = ({ onFileLoad }) => {
  const [isDragging, setIsDragging] = useState(false);

  const processFile = useCallback((file) => {
    const reader = new FileReader();
    reader.onload = (e) => {
      onFileLoad(e.target.result, file.name);
    };
    reader.readAsText(file);
  }, [onFileLoad]);

  const handleDrag = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
  }, []);

  const handleDragIn = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
    setIsDragging(true);
  }, []);

  const handleDragOut = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
    setIsDragging(false);
  }, []);

  const handleDrop = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
    setIsDragging(false);

    const file = e.dataTransfer.files[0];
    if (file) {
      processFile(file);
    }
  }, [processFile]);

  const handleFileSelect = useCallback((e) => {
    const file = e.target.files[0];
    if (file) {
      processFile(file);
    }
  }, [processFile]);

  const fileTypes = ['.js', '.py', '.java', '.tsx', '.css'];

  return (
    <div className="bg-white dark:bg-gray-900 rounded-xl p-8 shadow-lg transition-colors flex flex-col min-h-[500px]">
      <div className="flex items-center gap-2 mb-5">
        <span className="text-xl">📁</span>
        <h2 className="text-lg font-semibold text-gray-900 dark:text-white">Upload File</h2>
        <span className="ml-auto text-xs text-gray-500 dark:text-gray-400">Max 10MB</span>
      </div>

      <div
        className={`flex-1 border-2 border-dashed rounded-xl p-10 text-center transition-all cursor-pointer
                   flex flex-col items-center justify-center
                   ${isDragging 
                     ? 'border-indigo-500 bg-indigo-50 dark:bg-indigo-900/20' 
                     : 'border-gray-300 dark:border-gray-700 hover:border-indigo-500 hover:bg-indigo-50 dark:hover:bg-indigo-900/20'
                   }`}
        onDragEnter={handleDragIn}
        onDragLeave={handleDragOut}
        onDragOver={handleDrag}
        onDrop={handleDrop}
        onClick={() => document.getElementById('fileInput')?.click()}
      >
        <div className="w-16 h-16 bg-gradient-to-r from-indigo-500 to-purple-600 rounded-full 
                        flex items-center justify-center mx-auto mb-5 text-white text-3xl">
          ☁️
        </div>
        <div className="text-lg text-gray-900 dark:text-white mb-2">Drop your file here</div>
        <div className="text-sm text-gray-500 dark:text-gray-400">
          or <span className="text-indigo-600 dark:text-indigo-400 font-medium cursor-pointer">browse</span> from your computer
        </div>
        
        <input 
          type="file" 
          id="fileInput" 
          className="hidden" 
          onChange={handleFileSelect}
          accept=".js,.py,.java,.tsx,.css"
        />

        <div className="flex gap-2 justify-center mt-5 flex-wrap">
          {fileTypes.map(type => (
            <span key={type} className="px-3 py-1 bg-gray-100 dark:bg-gray-800 rounded-md text-xs 
                                       text-gray-600 dark:text-gray-400 hover:bg-gray-200 dark:hover:bg-gray-700 
                                       transition-colors">
              {type}
            </span>
          ))}
        </div>
      </div>
    </div>
  );
};

export default UploadSection;