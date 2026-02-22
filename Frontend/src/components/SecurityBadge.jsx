import React from 'react';

const SecurityBadge = () => {
  return (
    <div className="flex items-center gap-3 bg-green-50 dark:bg-green-900/20 
                    border border-green-200 dark:border-green-800 p-4 rounded-xl mb-8">
      <div className="w-10 h-10 bg-green-500 rounded-full flex items-center justify-center text-white text-xl">
        🛡️
      </div>
      <div>
        <h3 className="text-sm font-medium text-green-700 dark:text-green-400">Security Check Ready</h3>
        <p className="text-xs text-green-600 dark:text-green-500">Files are encrypted and auto-deleted</p>
      </div>
    </div>
  );
};

export default SecurityBadge;