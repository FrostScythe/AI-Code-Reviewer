import React from 'react';
import { useTheme } from '../contexts/useTheme';

const Header = () => {
  const { isDark, toggleTheme } = useTheme();

  return (
    <header className="flex justify-between items-center mb-10 bg-white dark:bg-gray-900 p-4 rounded-xl shadow-sm transition-colors">
      <div className="flex items-center gap-3">
        <div className="w-10 h-10 bg-gradient-to-r from-indigo-500 to-purple-600 rounded-xl flex items-center justify-center text-white text-2xl">
          🔍
        </div>
        <span className="text-xl font-semibold text-gray-900 dark:text-white">AI Code Review</span>
      </div>
      
      <div className="flex items-center gap-4">
        <button className="btn-primary flex items-center gap-2">
          <span>📝</span>
          Review
        </button>
        <button className="btn-secondary">
          History
        </button>
        <button 
          onClick={toggleTheme}
          className="w-10 h-10 rounded-full bg-gray-100 dark:bg-gray-800 flex items-center justify-center 
                     hover:bg-gray-200 dark:hover:bg-gray-700 transition-all duration-300 hover:rotate-180 text-xl"
        >
          {isDark ? '☀️' : '🌙'}
        </button>
      </div>
    </header>
  );
};

export default Header;