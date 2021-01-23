package org.deepspeech.libdeepspeech;

/**
 * @brief Exposes a DeepSpeech model in Java
 **/
public class DeepSpeechModel {

    // FIXME: We should have something better than those SWIGTYPE_*
    private SWIGTYPE_p_p_ModelState _mspp;
    private SWIGTYPE_p_ModelState _msp;

    private void evaluateErrorCode(int errorCode) {
        DeepSpeech_Error_Codes code = DeepSpeech_Error_Codes.swigToEnum(errorCode);
        if (code != DeepSpeech_Error_Codes.ERR_OK) {
            throw new RuntimeException("Error: " + impl.ErrorCodeToErrorMessage(errorCode) + " (0x" + Integer.toHexString(errorCode) + ").");
        }
    }

    /**
     * @param modelPath The path to the frozen model graph.
     * @throws RuntimeException on failure.
     * @brief An object providing an interface to a trained DeepSpeech model.
     * @constructor
     */
    public DeepSpeechModel(String modelPath) {
        this._mspp = impl.new_modelstatep();
        evaluateErrorCode(impl.CreateModel(modelPath, this._mspp));
        this._msp = impl.modelstatep_value(this._mspp);
    }

    /**
     * @return Beam width value used by the model.
     * @brief Get beam width value used by the model. If setModelBeamWidth was not
     * called before, will return the default value loaded from the model file.
     */
    public long beamWidth() {
        return impl.GetModelBeamWidth(this._msp);
    }

    /**
     * @param aBeamWidth The beam width used by the model. A larger beam width value
     *                   generates better results at the cost of decoding time.
     * @throws RuntimeException on failure.
     * @brief Set beam width value used by the model.
     */
    public void setBeamWidth(long beamWidth) {
        evaluateErrorCode(impl.SetModelBeamWidth(this._msp, beamWidth));
    }

    /**
     * @return Sample rate.
     * @brief Return the sample rate expected by the model.
     */
    public int sampleRate() {
        return impl.GetModelSampleRate(this._msp);
    }

    /**
     * @brief Frees associated resources and destroys model object.
     */
    public void freeModel() {
        impl.FreeModel(this._msp);
    }

    /**
     * @param scorer The path to the external scorer file.
     * @throws RuntimeException on failure.
     * @brief Enable decoding using an external scorer.
     */
    public void enableExternalScorer(String scorer) {
        evaluateErrorCode(impl.EnableExternalScorer(this._msp, scorer));
    }

    /**
     * @throws RuntimeException on failure.
     * @brief Disable decoding using an external scorer.
     */
    public void disableExternalScorer() {
        evaluateErrorCode(impl.DisableExternalScorer(this._msp));
    }

    /**
     * @param alpha The alpha hyperparameter of the decoder. Language model weight.
     * @param beta  The beta hyperparameter of the decoder. Word insertion weight.
     * @throws RuntimeException on failure.
     * @brief Enable decoding using beam scoring with a KenLM language model.
     */
    public void setScorerAlphaBeta(float alpha, float beta) {
        evaluateErrorCode(impl.SetScorerAlphaBeta(this._msp, alpha, beta));
    }

    /*
     * @brief Use the DeepSpeech model to perform Speech-To-Text.
     *
     * @param buffer A 16-bit, mono raw audio signal at the appropriate
     *                sample rate (matching what the model was trained on).
     * @param buffer_size The number of samples in the audio signal.
     *
     * @return The STT result.
     */
    public String stt(short[] buffer, int buffer_size) {
        return impl.SpeechToText(this._msp, buffer, buffer_size);
    }

    /**
     * @param buffer      A 16-bit, mono raw audio signal at the appropriate
     *                    sample rate (matching what the model was trained on).
     * @param buffer_size The number of samples in the audio signal.
     * @param num_results Maximum number of candidate transcripts to return. Returned list might be smaller than this.
     * @return Metadata struct containing multiple candidate transcripts. Each transcript
     * has per-token metadata including timing information.
     * @brief Use the DeepSpeech model to perform Speech-To-Text and output metadata
     * about the results.
     */
    public Metadata sttWithMetadata(short[] buffer, int buffer_size, int num_results) {
        return impl.SpeechToTextWithMetadata(this._msp, buffer, buffer_size, num_results);
    }

    /**
     * @return An opaque object that represents the streaming state.
     * @throws RuntimeException on failure.
     * @brief Create a new streaming inference state. The streaming state returned
     * by this function can then be passed to feedAudioContent()
     * and finishStream().
     */
    public DeepSpeechStreamingState createStream() {
        SWIGTYPE_p_p_StreamingState ssp = impl.new_streamingstatep();
        evaluateErrorCode(impl.CreateStream(this._msp, ssp));
        return new DeepSpeechStreamingState(impl.streamingstatep_value(ssp));
    }

    /**
     * @param cctx        A streaming state pointer returned by createStream().
     * @param buffer      An array of 16-bit, mono raw audio samples at the
     *                    appropriate sample rate (matching what the model was trained on).
     * @param buffer_size The number of samples in @p buffer.
     * @brief Feed audio samples to an ongoing streaming inference.
     */
    public void feedAudioContent(DeepSpeechStreamingState ctx, short[] buffer, int buffer_size) {
        impl.FeedAudioContent(ctx.get(), buffer, buffer_size);
    }

    /**
     * @param ctx A streaming state pointer returned by createStream().
     * @return The STT intermediate result.
     * @brief Compute the intermediate decoding of an ongoing streaming inference.
     */
    public String intermediateDecode(DeepSpeechStreamingState ctx) {
        return impl.IntermediateDecode(ctx.get());
    }

    /**
     * @param ctx         A streaming state pointer returned by createStream().
     * @param num_results Maximum number of candidate transcripts to return. Returned list might be smaller than this.
     * @return The STT intermediate result.
     * @brief Compute the intermediate decoding of an ongoing streaming inference.
     */
    public Metadata intermediateDecodeWithMetadata(DeepSpeechStreamingState ctx, int num_results) {
        return impl.IntermediateDecodeWithMetadata(ctx.get(), num_results);
    }

    /**
     * @param ctx A streaming state pointer returned by createStream().
     * @return The STT result.
     * @brief Compute the final decoding of an ongoing streaming inference and return
     * the result. Signals the end of an ongoing streaming inference.
     * @note This method will free the state pointer (@p ctx).
     */
    public String finishStream(DeepSpeechStreamingState ctx) {
        return impl.FinishStream(ctx.get());
    }

    /**
     * @param ctx         A streaming state pointer returned by createStream().
     * @param num_results Maximum number of candidate transcripts to return. Returned list might be smaller than this.
     * @return Metadata struct containing multiple candidate transcripts. Each transcript
     * has per-token metadata including timing information.
     * @brief Compute the final decoding of an ongoing streaming inference and return
     * the results including metadata. Signals the end of an ongoing streaming
     * inference.
     * @note This method will free the state pointer (@p ctx).
     */
    public Metadata finishStreamWithMetadata(DeepSpeechStreamingState ctx, int num_results) {
        return impl.FinishStreamWithMetadata(ctx.get(), num_results);
    }

    /**
     * @param word
     * @param boost
     * @throws RuntimeException on failure.
     * @brief Add a hot-word
     */
    public void addHotWord(String word, float boost) {
        evaluateErrorCode(impl.AddHotWord(this._msp, word, boost));
    }

    /**
     * @param word
     * @throws RuntimeException on failure.
     * @brief Erase a hot-word
     */
    public void eraseHotWord(String word) {
        evaluateErrorCode(impl.EraseHotWord(this._msp, word));
    }

    /**
     * @throws RuntimeException on failure.
     * @brief Clear all hot-words.
     */
    public void clearHotWords() {
        evaluateErrorCode(impl.ClearHotWords(this._msp));
    }
}
