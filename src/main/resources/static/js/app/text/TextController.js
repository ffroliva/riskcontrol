'use strict'
app.controller("TextController", function TextController($scope, TextService) {

        var vm = this;
        
        // attributes
        vm.text = {};
        vm.word1 = '';
        vm.word2 = '';
        vm.error = '';

        // methods
        vm.readFile = function() {
            TextService.readFile().then(function(value) {
                console.log(value.data);
                vm.text = value.data;
            }, function(reason) {
                console.log("error occured" + reason);
            }, function(value) {
                console.log("no callback" + value);
            });
        }
        vm.replaceWordsFromFile = function() {
            vm.error = '';
            if(vm.word1 === ''){
                vm.error = 'Enter the word to be replaced';
                return;
            }
            if(vm.word2 === '') {
                vm.error = 'Enter the word to be replaced with';
            }
            TextService.replaceWordsFromFile(vm.word1, vm.word2).then(function(value) {
                console.log(value.data);
                vm.text = value.data;
            }, function(reason) {
                console.log("error occured" + reason);
            }, function(value) {
                console.log("no callback" + value);
            });
        }
});
