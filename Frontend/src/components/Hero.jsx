import React from 'react';

const Hero = () => {
  return (
    <div className="text-center mb-12">
      <div className="inline-block bg-indigo-50 dark:bg-indigo-900/30 text-indigo-600 dark:text-indigo-400 
                      px-4 py-2 rounded-full text-xs font-semibold tracking-wider mb-5">
        🔮 INTELLIGENT ANALYSIS
      </div>
      <h1 className="text-4xl md:text-5xl font-bold mb-5 text-gray-900 dark:text-white">
        Review code <span className="gradient-text">faster & safer</span>
      </h1>
      <p className="text-lg text-gray-600 dark:text-gray-400 max-w-2xl mx-auto leading-relaxed">
        Securely upload files or paste snippets. Our AI detects bugs, security flaws, 
        and suggests optimizations in seconds.
      </p>
    </div>
  );
};

export default Hero;