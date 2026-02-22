import React from 'react';

const features = [
  {
    icon: '🛡️',
    title: 'Secure Check',
    description: 'Enterprise-grade encryption ensures your intellectual property remains private and is deleted post-analysis.',
    color: 'blue'
  },
  {
    icon: '⚡',
    title: 'Lightning Fast',
    description: 'Get instant feedback on syntax errors, logic bugs, and style violations with our optimized AI engine.',
    color: 'purple'
  },
  {
    icon: '🕐',
    title: 'Track History',
    description: 'Access your previous analyses to track improvements and maintain code quality standards over time.',
    color: 'orange'
  }
];

const colorClasses = {
  blue: 'bg-blue-50 dark:bg-blue-900/20 text-blue-600 dark:text-blue-400',
  purple: 'bg-purple-50 dark:bg-purple-900/20 text-purple-600 dark:text-purple-400',
  orange: 'bg-orange-50 dark:bg-orange-900/20 text-orange-600 dark:text-orange-400'
};

const Features = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
      {features.map((feature, index) => (
        <div key={index} 
             className="bg-white dark:bg-gray-900 p-8 rounded-xl shadow-lg border border-gray-200 
                        dark:border-gray-800 hover:-translate-y-1 hover:shadow-xl transition-all duration-300">
          <div className={`w-14 h-14 rounded-xl flex items-center justify-center text-2xl mb-5 ${colorClasses[feature.color]}`}>
            {feature.icon}
          </div>
          <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-3">{feature.title}</h3>
          <p className="text-sm text-gray-600 dark:text-gray-400 leading-relaxed">{feature.description}</p>
        </div>
      ))}
    </div>
  );
};

export default Features;