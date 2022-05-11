const express = require("express")
const router = express()
const multer = require("multer")
const upload = multer({ dest: "uploads/" })
const usluge = require("../model/usluga")
const radnici = require("../model/radnik")

router.get("/radnici", async (req, res) => {
    try {
        const sviRadnici = await radnici.find()
        res.status(200).json({
            uspeh: true,
            radnici: sviRadnici
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.get("/radnici/:id", async (req, res) => {
    try {
        const id = req.params.id
        const jedanRadnik = await radnici.findOne({"naziv": `${id}`})
        res.status(200).json({
            uspeh: true,
            radnik: jedanRadnik
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.post("/radnici", upload.single("avatar"), async (req, res) => {
    try {
        const noviRadnik = new radnici(req.body)
        // noviRadnik.avatar = req.file.filename
        await noviRadnik.save()
        await usluge.findOne({"tip": `${req.body.tipUsluge}`}, async (err, jednaUsluga) => {
            try {
                if (err) { console.log(err) }
                if (!jednaUsluga) {
                    const novaUsluga = new usluge({
                        "tip": `${req.body.tipUsluge}`
                    })
                    novaUsluga.radnici.push(req.body.naziv)
                    novaUsluga.kolicina++
                    await novaUsluga.save()
                } else {
                    jednaUsluga.radnici.push(req.body.naziv)
                    jednaUsluga.kolicina++
                    await jednaUsluga.save()
                }
            } catch (err) {
                console.log(err)
            }
        })
        res.status(200).json({
            uspeh: true
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

router.delete("/radnici/:id", async (req, res) => {
    try {
        const id = req.params.id
        const jedanRadnik = await radnici.findOne({"naziv": `${id}`})
        await jedanRadnik.delete()
        res.status(200).json({
            uspeh: true
        })
    } catch (err) {
        res.status(404).json({
            uspeh: false,
            message: err.message
        })
    }
})

module.exports = router