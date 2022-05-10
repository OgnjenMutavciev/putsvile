const mongoose = require("mongoose")

const uslugaSchema = new mongoose.Schema({
    tip: {
        type: String,
        required: true,
        unique: true
    },
    radnici: [String],
    kolicina: { type: Number, default: 0 }
}, { collection: "usluge" })

module.exports = mongoose.model("usluga", uslugaSchema)