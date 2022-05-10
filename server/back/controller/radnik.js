const express = require("express")
const router = express()
const multer = require("multer")
const upload = multer({ dest: "uploads/" })
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