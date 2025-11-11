import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { GoogleGenerativeAI } from "@google/generative-ai";

export function AiPromptModal({
  open,
  onClose,
  onInsert,
}: {
  open: boolean;
  onClose: () => void;
  onInsert: (content: string | object) => void;
}) {
  const [prompt, setPrompt] = useState(
    "Viết đoạn mở bài hấp dẫn cho bài blog về sức khỏe tiêu hoá (~120-150 từ), giọng thân thiện."
  );
  const [format, setFormat] = useState<"html" | "tiptap">("html");
  const [loading, setLoading] = useState(false);

  if (!open) return null;

  const genAI = new GoogleGenerativeAI(import.meta.env.VITE_GEMINI_API_KEY);

  const generate = async () => {
    try {
      setLoading(true);

      const model = genAI.getGenerativeModel({
        model: "gemini-2.5-flash",
        generationConfig: {
          temperature: 0.7,
          maxOutputTokens: 2048,
          responseMimeType:
            format === "html" ? "text/plain" : "application/json",
        },
        systemInstruction:
          format === "html"
            ? `
You are an AI content generator producing HTML.

REQUIREMENTS:
- OUTPUT ONLY VALID HTML.
- DO NOT return explanations.
- DO NOT return markdown.
- DO NOT include backticks or code fences.
- Use <h2>, <h3>, <p>, <ul>, <li>, <blockquote>.
- DO NOT wrap inside <html> or <body>.
- NO extra text outside HTML.
`
            : `
You are an AI content generator producing TipTap JSON v2.

REQUIREMENTS:
- OUTPUT ONLY VALID JSON.
- DO NOT include explanations or text outside JSON.
- MUST start with: {"type":"doc","content":[...]}
- Use correct TipTap nodes:
  {"type":"paragraph","content":[{"type":"text","text":"..."}]}
  {"type":"heading","attrs":{"level":2}, "content":[...]}
  {"type":"bulletList","content":[...]}
  {"type":"listItem","content":[...]}
- DO NOT return HTML or markdown.
- NO backticks. NO code fences.
`,
      });

      const res = await model.generateContent(prompt);
      const raw = res.response.text();

      if (!raw) throw new Error("No content generated.");

      if (format === "html") {
        onInsert(raw);
        return;
      }
      let json;
      try {
        json = JSON.parse(raw);
      } catch {
        console.error("RAW JSON:", raw);
        throw new Error("Invalid JSON returned by AI.");
      }

      if (!json.type || json.type !== "doc") {
        console.error("Invalid TipTap JSON:", json);
        throw new Error("Not in TipTap doc format.");
      }

      onInsert(json);
    } catch (e: any) {
      alert(e?.message || "Generate failed.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/30 z-50 flex items-center justify-center">
      <div className="bg-white dark:bg-neutral-900 w-full max-w-2xl rounded-xl p-4 space-y-3 shadow-lg">
        <div className="text-lg font-semibold">AI Content</div>
        <Textarea
          value={prompt}
          onChange={(e) => setPrompt(e.target.value)}
          rows={6}
        />
        <div className="flex items-center gap-3">
          <label className="text-sm">Format:</label>
          <select
            className="border rounded px-2 py-1"
            value={format}
            onChange={(e) => setFormat(e.target.value as any)}
          >
            <option value="html">HTML</option>
            <option value="tiptap">TipTap JSON</option>
          </select>
          <div className="flex-1" />
          <Button disabled={loading} onClick={generate}>
            {loading ? "Generating..." : "Generate & Insert"}
          </Button>
          <Button variant="outline" onClick={onClose}>
            Close
          </Button>
        </div>
        <div className="text-xs text-neutral-500">
          Hint: Bạn có thể yêu cầu bullet list, heading H2/H3, chèn blockquote,
          hoặc viết lại đoạn text đang chọn.
        </div>
      </div>
    </div>
  );
}
