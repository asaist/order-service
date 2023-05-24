function getIndex(list, id) {
    for (var i = 0; i < list.length; i++){
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}


var trackApi = Vue.resource('/track{/id}');

Vue.component('track-form', {
    props:['tracks', 'trackAttr'],
    data: function (){
        return {
            name: '',
            id: ''
        }
    },
    watch: {
        trackAttr: function(newVal, oldVal){
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="name" placeholder="Write something" v-model="name" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>' +
        '<div>' +
            '<input type="name1" placeholder="Write something" v-model="name1" />' +
        '</div>'
    ,

    methods: {
        save: function () {
            var track = {name: this.name};

            if (this.id) {
                trackApi.update({id: this.id}, track).then(result =>
                    result.json().then(data =>{
                        var index = getIndex(this.tracks, data.id);
                        this.tracks.splice(index, 1, data);
                        this.name = ''
                        this.id = ''
                    })
                )
            } else {
                trackApi.save({}, track).then(result =>
                    result.json().then(data => {
                        this.tracks.push(data);
                        this.name = ''
                    })
                )
            }
        }
    }
})

Vue.component('track-row',{
    props: ['track', 'editMethod', 'tracks'],
    template:
        '<div>' +
        '<i>({{ track.id }})</i> {{ track.name }} ' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.track);
        },
        del: function () {
            trackApi.remove({id: this.track.id}).then(result => {
                if (result.ok) {
                    this.tracks.splice(this.tracks.indexOf(this.track), 1)
                }
            })
        }
    }
});


Vue.component('tracks-list', {
    props:['tracks'],
    data: function() {
        return {
            track: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<track-form :tracks="tracks" :trackAttr="track" />' +
        '<track-row v-for="track in tracks" :key="track.id" :track="track"' +
        ':editMethod="editMethod" :tracks="tracks" />' +
        '</div>',
    created: function() {
        trackApi.get().then(result =>
            result.json().then(data =>
                data.forEach(track => this.tracks.push(track))
            )
        )
    },
    methods: {
        editMethod: function (track) {
            this.track = track;
        }
    }
})


var app = new Vue({
    el: '#app',
    template: '<tracks-list :tracks="tracks" />',
    data: {
        tracks: []
    }
})