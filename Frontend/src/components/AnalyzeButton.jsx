import React from 'react';

const AnalyzeButton = ({ onClick, disabled }) => {
  return (
    <button
      onClick={onClick}
      disabled={disabled}
      className={`w-full py-4 bg-gradient-to-r from-indigo-500 to-purple-600 text-white 
                 rounded-xl font-semibold flex items-center justify-center gap-2
                 transition-all duration-300 hover:shadow-lg hover:shadow-indigo-500/30 
                 hover:-translate-y-0.5 ${disabled ? 'opacity-50 cursor-not-allowed' : ''}`}
    >
      <span>🔧</span>
      Analyse Code Now
    </button>
  );
};

export default AnalyzeButton;