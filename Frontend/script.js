const uploadArea = document.getElementById('uploadArea');
        const fileInput = document.getElementById('fileInput');
        const browseLink = document.getElementById('browseLink');
        const analyzeBtn = document.getElementById('analyzeBtn');
        const codeInput = document.getElementById('codeInput');

        // File upload functionality
        browseLink.addEventListener('click', (e) => {
            e.preventDefault();
            fileInput.click();
        });

        fileInput.addEventListener('change', (e) => {
            const file = e.target.files[0];
            if (file) {
                handleFile(file);
            }
        });

        // Drag and drop
        uploadArea.addEventListener('dragover', (e) => {
            e.preventDefault();
            uploadArea.classList.add('dragover');
        });

        uploadArea.addEventListener('dragleave', () => {
            uploadArea.classList.remove('dragover');
        });

        uploadArea.addEventListener('drop', (e) => {
            e.preventDefault();
            uploadArea.classList.remove('dragover');
            const file = e.dataTransfer.files[0];
            if (file) {
                handleFile(file);
            }
        });

        function handleFile(file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                codeInput.value = e.target.result;
                alert(`File "${file.name}" loaded successfully!`);
            };
            reader.readAsText(file);
        }

        // Clear button
        document.querySelectorAll('.icon-btn')[0].addEventListener('click', () => {
            codeInput.value = '';
        });

        // Copy button
        document.querySelectorAll('.icon-btn')[1].addEventListener('click', () => {
            codeInput.select();
            document.execCommand('copy');
            alert('Code copied to clipboard!');
        });

        // Analyze button
        analyzeBtn.addEventListener('click', () => {
            const code = codeInput.value.trim();
            if (code) {
                alert('Analyzing your code... This would trigger the AI analysis in production!');
            } else {
                alert('Please paste some code or upload a file first.');
            }
        });

        // Theme toggle
        const themeToggle = document.querySelector('.theme-toggle');
        const html = document.documentElement;

        // Check for saved theme preference or default to light mode
        const currentTheme = localStorage.getItem('theme') || 'light';
        if (currentTheme === 'dark') {
            document.body.classList.add('dark-mode');
            themeToggle.textContent = '☀️';
        } else {
            themeToggle.textContent = '🌙';
        }

        themeToggle.addEventListener('click', function() {
            document.body.classList.toggle('dark-mode');

            if (document.body.classList.contains('dark-mode')) {
                themeToggle.textContent = '☀️';
                localStorage.setItem('theme', 'dark');
            } else {
                themeToggle.textContent = '🌙';
                localStorage.setItem('theme', 'light');
            }
        });