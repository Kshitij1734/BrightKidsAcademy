function startNumberRecognitionGame() {
        const content = document.getElementById('number-recognition-content');
        content.style.display = content.style.display === 'none' ? 'block' : 'none';
        generateNumber();
    }

    function generateNumber() {
        const numberToRecognize = Math.floor(Math.random() * 9) + 1;
        const numberElement = document.getElementById('number-to-recognize');
        numberElement.textContent = numberToRecognize;
        numberElement.style.color = 'black'; // Reset color to default

        // Play the corresponding number sound
        playNumberSound(numberToRecognize);
    }

    function playNumberSound(number) {
        const sound = document.getElementById(`sound-${number}`);
        if (sound) {
            sound.play();
        }
    }

    function checkNumber(number) {
        const numberElement = document.getElementById('number-to-recognize');
        const numberToRecognize = parseInt(numberElement.textContent);

        if (number === numberToRecognize) {
            numberElement.style.color = 'green';
            alert('Success!');
            setTimeout(generateNumber, 1000); // Generate new number after 1 second
        } else {
            numberElement.style.color = 'red';
            alert('Try again!');
            
        }
    }