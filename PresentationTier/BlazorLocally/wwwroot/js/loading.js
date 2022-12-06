(function () {
    let loadingImages = [
        "apple", 
        "avocado",
        "beetroot",
        "carrot",
        "cauliflower",
        "cherry",
        "corn",
        "eggplant",
        "paprika",
        "pumpkin",
        "salad",
        "strawberry"];


    let loadingWrapper = document.querySelector('.loading-wrapper');
    let grayImage = loadingWrapper.querySelector('.gray img');
    let colorImage = loadingWrapper.querySelector('.color .image');
    let percentageBox = loadingWrapper.querySelector('.loading-text .loading-percentage');

    let randomNum = Math.floor(Math.random() * loadingImages.length);
    let randomImage = `Img/loading/${loadingImages[randomNum]}.png`;

    setImage(randomImage)

    function setImage(imagePath) {
        grayImage.src = imagePath;
        colorImage.style.backgroundImage = `url(${imagePath})`;
    }

    function setPercentage(percentage) {
        loadingWrapper.style.setProperty('--loading-percentage', percentage + "");
        percentageBox.textContent = percentage + "%";
    }

    function StartBlazor() {
        let loadedCount = 0;
        const resourcesToLoad = [];
        Blazor.start({
            loadBootResource:
                function (type, filename, defaultUri, integrity) {
                    if (type === "dotnetjs")
                        return defaultUri;

                    const fetchResources = fetch(defaultUri, {
                        cache: 'no-cache',
                        integrity: integrity,
                    });
                    

                    resourcesToLoad.push(fetchResources);

                    fetchResources.then((r) => {
                        loadedCount += 1;
                        
                        const totalCount = resourcesToLoad.length;
                        let numObject = { num: 0 }
                        numObject.num = parseInt(loadedCount / totalCount * 100);
                        
                        let ignore = filename === "blazor.boot.json" && !checkIfAllLoaded(numObject);
                        
                        console.log(`Loaded ${filename} (${loadedCount}/${totalCount}) ${numObject.num}%`);
                        setPercentage(numObject.num);
                    });

                    return fetchResources;
                }
        });
        
        function checkIfAllLoaded(numObject) {
            caches.keys().then(key => {
                if(key instanceof Array && key.length > 0 && key["0"].includes("blazor")){
                    numObject.num = 100;
                    setPercentage(numObject.num);
                }
            })
            
            numObject.num = 0;
            return false;
        }
    }

    //StartBlazor();
})();