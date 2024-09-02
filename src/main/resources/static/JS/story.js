
        document.addEventListener('DOMContentLoaded', () => {
            const storyList = document.getElementById('story-list');
            const stories = document.querySelectorAll('.story');

            storyList.addEventListener('click', (event) => {
                if (event.target.tagName === 'LI') {
                    const selectedStoryId = event.target.getAttribute('data-story');
                    stories.forEach(story => {
                        if (story.id === selectedStoryId) {
                            story.classList.add('active');
                            story.style.display = 'block';
                        } else {
                            story.classList.remove('active');
                            story.style.display = 'none';
                        }
                    });
                }
            });
        });
